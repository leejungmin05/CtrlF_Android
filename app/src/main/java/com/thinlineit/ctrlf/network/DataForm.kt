package com.thinlineit.ctrlf.network

data class IdTitle(val id : Int, val title : String)
data class HomeInformation(val count : Int, val notes : List<IdTitle>, val issues : List<IdTitle>)
