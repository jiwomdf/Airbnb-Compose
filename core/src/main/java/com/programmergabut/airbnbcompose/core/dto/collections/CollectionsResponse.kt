package com.programmergabut.airbnbcompose.core.dto.collections

import kotlinx.serialization.Serializable

@Serializable
data class CollectionsResponse(
    val results: List<Result?>?,
    val total: Int?,
    val total_pages: Int?
) {
    @Serializable
    data class Result(
        val cover_photo: CoverPhoto?,
        val curated: Boolean?,
        val description: String?,
        val featured: Boolean?,
        val id: String?,
        val last_collected_at: String?,
        val links: Links?,
        val preview_photos: List<PreviewPhoto?>?,
        val `private`: Boolean?,
        val published_at: String?,
        val share_key: String?,
        val title: String?,
        val total_photos: Int?,
        val updated_at: String?,
        val user: User?
    ) {
        @Serializable
        data class CoverPhoto(
            val alt_description: String?,
            val blur_hash: String?,
            val color: String?,
            val created_at: String?,
            val description: String?,
            val height: Int?,
            val id: String?,
            val liked_by_user: Boolean?,
            val likes: Int?,
            val links: Links?,
            val promoted_at: String?,
            val updated_at: String?,
            val urls: Urls?,
            val user: User?,
            val width: Int?
        ) {
            @Serializable
            data class Links(
                val download: String?,
                val download_location: String?,
                val html: String?,
                val self: String?
            )

            @Serializable
            data class Urls(
                val full: String?,
                val raw: String?,
                val regular: String?,
                val small: String?,
                val small_s3: String?,
                val thumb: String?
            )

            @Serializable
            data class User(
                val accepted_tos: Boolean?,
                val bio: String?,
                val first_name: String?,
                val for_hire: Boolean?,
                val id: String?,
                val instagram_username: String?,
                val last_name: String?,
                val links: Links?,
                val location: String?,
                val name: String?,
                val portfolio_url: String?,
                val profile_image: ProfileImage?,
                val social: Social?,
                val total_collections: Int?,
                val total_likes: Int?,
                val total_photos: Int?,
                val twitter_username: String?,
                val updated_at: String?,
                val username: String?
            ) {
                @Serializable
                data class Links(
                    val followers: String?,
                    val following: String?,
                    val html: String?,
                    val likes: String?,
                    val photos: String?,
                    val portfolio: String?,
                    val self: String?
                )

                @Serializable
                data class ProfileImage(
                    val large: String?,
                    val medium: String?,
                    val small: String?
                )

                @Serializable
                data class Social(
                    val instagram_username: String?,
                    val portfolio_url: String?,
                    val twitter_username: String?
                )
            }
        }

        @Serializable
        data class Links(
            val html: String?,
            val photos: String?,
            val related: String?,
            val self: String?
        )

        @Serializable
        data class PreviewPhoto(
            val blur_hash: String?,
            val created_at: String?,
            val id: String?,
            val updated_at: String?,
            val urls: Urls?
        ) {
            @Serializable
            data class Urls(
                val full: String?,
                val raw: String?,
                val regular: String?,
                val small: String?,
                val small_s3: String?,
                val thumb: String?
            )
        }

        @Serializable
        data class User(
            val accepted_tos: Boolean?,
            val bio: String?,
            val first_name: String?,
            val for_hire: Boolean?,
            val id: String?,
            val instagram_username: String?,
            val last_name: String?,
            val links: Links?,
            val location: String?,
            val name: String?,
            val portfolio_url: String?,
            val profile_image: ProfileImage?,
            val social: Social?,
            val total_collections: Int?,
            val total_likes: Int?,
            val total_photos: Int?,
            val twitter_username: String?,
            val updated_at: String?,
            val username: String?
        ) {
            @Serializable
            data class Links(
                val followers: String?,
                val following: String?,
                val html: String?,
                val likes: String?,
                val photos: String?,
                val portfolio: String?,
                val self: String?
            )

            @Serializable
            data class ProfileImage(
                val large: String?,
                val medium: String?,
                val small: String?
            )

            @Serializable
            data class Social(
                val instagram_username: String?,
                val portfolio_url: String?,
                val twitter_username: String?
            )
        }
    }
}