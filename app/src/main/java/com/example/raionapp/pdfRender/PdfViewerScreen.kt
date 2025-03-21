package com.example.raionapp.pdfRender

import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PdfViewerScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val pdfBitmapConverter = remember {
        PdfBitmapConverter(context)
    }
    var pdfUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var renderedPages by remember {
        mutableStateOf<List<Bitmap>>(emptyList())
    }
    var searchText by remember {
        mutableStateOf("")
    }
    var searchResults by remember {
        mutableStateOf(emptyList<SearchResults>())
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(pdfUri) {
        pdfUri?.let { uri ->
            Log.d("PDF_URI", uri.toString())
            renderedPages = pdfBitmapConverter.pdfToBitmaps(uri)
        }
    }

    val choosePdfLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        Log.d("PDF_URI", it.toString())
        pdfUri = it
    }

    if(pdfUri == null) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                choosePdfLauncher.launch("application/pdf")
            }) {
                Text(text = "Choose PDF")
            }
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                itemsIndexed(renderedPages) { index, page ->
                    PdfPage(
                        page = page,
                        searchResults = searchResults.find { it.page == index }
                    )
                }
            }
            Button(onClick = {
                choosePdfLauncher.launch("application/pdf")
            }) {
                Text(text = "Choose another PDF")
            }
            if(Build.VERSION.SDK_INT >= 35) {
                OutlinedTextField(
                    value = searchText,
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        if(searchText.isNotEmpty()) {
                            IconButton(onClick = {
                                searchText = ""
                                searchResults = emptyList()
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Clear search"
                                )
                            }
                        }
                    },
                    onValueChange = { newSearchText ->
                        searchText = newSearchText

                        pdfBitmapConverter.renderer?.let { renderer ->
                            scope.launch(Dispatchers.Default) {
                                searchResults = (0 until renderer.pageCount).map { index ->
                                    renderer.openPage(index).use { page ->
                                        val results = page.searchText(newSearchText)

                                        val matchedRects = results.map {
                                            it.bounds.first()
                                        }

                                        SearchResults(
                                            page = index,
                                            results = matchedRects
                                        )
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}