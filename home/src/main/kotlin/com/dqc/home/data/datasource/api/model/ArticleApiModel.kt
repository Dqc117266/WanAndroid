package com.dqc.home.data.datasource.api.model

import com.dqc.home.domain.model.Article
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ArticleApiModel(
    @SerialName("adminAdd") val adminAdd: Boolean,
    @SerialName("apkLink") val apkLink: String,
    @SerialName("audit") val audit: Int,
    @SerialName("author") val author: String,
    @SerialName("canEdit") val canEdit: Boolean,
    @SerialName("chapterId") val chapterId: Int,
    @SerialName("chapterName") val chapterName: String,
    @SerialName("collect") val collect: Boolean,
    @SerialName("courseId") val courseId: Int,
    @SerialName("desc") val desc: String,
    @SerialName("descMd") val descMd: String,
    @SerialName("envelopePic") val envelopePic: String,
    @SerialName("fresh") val fresh: Boolean,
    @SerialName("host") val host: String,
    @SerialName("id") val id: Int,
    @SerialName("isAdminAdd") val isAdminAdd: Boolean,
    @SerialName("link") val link: String,
    @SerialName("niceDate") val niceDate: String,
    @SerialName("niceShareDate") val niceShareDate: String,
    @SerialName("origin") val origin: String,
    @SerialName("prefix") val prefix: String,
    @SerialName("projectLink") val projectLink: String,
    @SerialName("publishTime") val publishTime: Long,
    @SerialName("realSuperChapterId") val realSuperChapterId: Int,
    @SerialName("selfVisible") val selfVisible: Int,
    @SerialName("shareDate") val shareDate: Long,
    @SerialName("shareUser") val shareUser: String,
    @SerialName("superChapterId") val superChapterId: Int,
    @SerialName("superChapterName") val superChapterName: String,
    @SerialName("tags") val tags: List<TagApiModel>,
    @SerialName("title") val title: String,
    @SerialName("type") val type: Int,
    @SerialName("userId") val userId: Int,
    @SerialName("visible") val visible: Int,
    @SerialName("zan") val zan: Int
)

internal fun ArticleApiModel.toDomainModel(): Article {
    return Article(
        adminAdd = this.adminAdd,
        apkLink = this.apkLink,
        audit = this.audit,
        author = this.author,
        canEdit = this.canEdit,
        chapterId = this.chapterId,
        chapterName = this.chapterName,
        collect = this.collect,
        courseId = this.courseId,
        desc = this.desc,
        descMd = this.descMd,
        envelopePic = this.envelopePic,
        fresh = this.fresh,
        host = this.host,
        id = this.id,
        isAdminAdd = this.isAdminAdd,
        link = this.link,
        niceDate = this.niceDate,
        niceShareDate = this.niceShareDate,
        origin = this.origin,
        prefix = this.prefix,
        projectLink = this.projectLink,
        publishTime = this.publishTime,
        realSuperChapterId = this.realSuperChapterId,
        selfVisible = this.selfVisible,
        shareDate = this.shareDate,
        shareUser = this.shareUser,
        superChapterId = this.superChapterId,
        superChapterName = this.superChapterName,
        tags = this.tags.map { it.toDomainModel() },
        title = this.title,
        type = this.type,
        userId = this.userId,
        visible = this.visible,
        zan = this.zan
    )
}