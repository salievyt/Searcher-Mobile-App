package online.saliev.searcher.viewmodel

class Links {

    fun searchTelegam(text: String): String {
        val teleUrl = "https://t.me/" + text
        return teleUrl
    }

    fun searchWhatsapp(numder: String): String {
        val whatUrl = "https://api.whatsapp.com/send?phone=" + numder
        return whatUrl
    }
}