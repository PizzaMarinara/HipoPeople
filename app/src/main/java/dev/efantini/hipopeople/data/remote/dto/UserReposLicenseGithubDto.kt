package dev.efantini.hipopeople.data.remote.dto

import com.squareup.moshi.Json

data class UserReposLicenseGithubDto(
    val key: String,
    val name: String,
    @Json(name = "spdx_id")
    val spdxId: String,
    val url: String,
    @Json(name = "node_id")
    val nodeId: String
)
