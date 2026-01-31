package app.arteh.easydialer.clog.models

data class Clog(
    val name: String,
    val number: String,
    val status: Int,
    val date: Long,
    val simID: Int,
    val key: Int
)