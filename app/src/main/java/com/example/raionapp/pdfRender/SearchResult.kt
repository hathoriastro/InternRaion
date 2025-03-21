package com.example.raionapp.pdfRender

import android.graphics.RectF

data class SearchResults(
    val page: Int,
    val results: List<RectF>
)