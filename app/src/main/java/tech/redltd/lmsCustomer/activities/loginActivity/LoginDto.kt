package tech.redltd.lmsCustomer.activities.loginActivity


data class LoginResponse(
    val success:Boolean,
    val payload: Any,
    val message:String
)

data class LoginBody(
    val phone:String,
    val password: String,
    val imei:String,
    val device_id:String
)