package com.yt8492.dirtycode.data.converter

import com.yt8492.dirtycode.data.json.ArticleJson
import com.yt8492.dirtycode.data.json.GroupJson
import com.yt8492.dirtycode.data.json.TagJson
import com.yt8492.dirtycode.data.json.UserJson
import com.yt8492.dirtycode.data.model.Article
import com.yt8492.dirtycode.data.model.Group
import com.yt8492.dirtycode.data.model.Tag
import com.yt8492.dirtycode.data.model.User

fun UserJson.toModel(): User = User(
        id = this.id,
        name = this.name,
        description = this.description,
        websiteUrl = this.websiteUrl,
        profileImageUrl = this.profileImageUrl,
        twitterName = this.twitterName,
        githubId = this.githubId,
        facebookId = this.facebookId,
        linkedinId = this.linkedinId,
        organization = this.organization,
        location = this.location,
        followeesCount = this.followeesCount,
        followersCount = this.followersCount,
        articleCount = this.articleCount,
        isTeamOnly = this.isTeamOnly
    )

fun TagJson.toModel(): Tag = Tag(
        name = this.name,
        iconUrl = this.iconUrl,
        followersCount = this.followersCount,
        itemsCount = this.itemsCount
    )

fun GroupJson.toModel(): Group = Group(
        id = this.id,
        name = this.name,
        urlName = this.urlName,
        isPrivate = this.isPrivate,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )

fun ArticleJson.toModel(): Article = Article(
        id = this.id,
        title = this.title,
        url = this.url,
        body = this.body,
        renderedBody = this.renderedBody,
        user = this.user.toModel(),
        tags = this.tags.map { it.name },
        group = this.group?.toModel(),
        commentsCount = this.commentsCount,
        likesCount = this.likesCount,
        viewCount = this.viewCount ?: 0,
        reactionsCount = this.reactionsCount,
        isPrivate = this.isPrivate,
        coediting = this.coediting,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )