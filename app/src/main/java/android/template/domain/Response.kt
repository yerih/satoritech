package android.template.domain


data class Response<T>(
    val value: T?,
    val error: Error? = null,
){
    fun isValid(): Boolean = (value != null)
    fun isError(): Boolean = (error != null)
}