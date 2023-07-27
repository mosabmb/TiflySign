package com.example.app.navigation


import com.example.core.designsystem.icon.Icon
import com.example.core.designsystem.icon.Icon.DrawableResourceIcon
import com.example.core.designsystem.icon.Icon.ImageVectorIcon
import com.example.core.designsystem.icon.TiflyIcons
import com.example.tiflysignapp.R


enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val iconTextId: Int,
    val titleTextId: Int,
) {

    HOME(
        selectedIcon = DrawableResourceIcon(TiflyIcons.Upcoming),
        unselectedIcon = DrawableResourceIcon(TiflyIcons.UpcomingBorder),
        iconTextId = R.string.Home,
        titleTextId = 1,
    ),

    CATEGORY(
        selectedIcon = DrawableResourceIcon(TiflyIcons.Bookmarks),
        unselectedIcon = DrawableResourceIcon(TiflyIcons.BookmarksBorder),
        iconTextId = R.string.Categories,
        titleTextId = 2,
    ),
    SEARCH(
        selectedIcon = ImageVectorIcon(TiflyIcons.Search),
        unselectedIcon = ImageVectorIcon(TiflyIcons.Search),
        iconTextId = R.string.Search,
        titleTextId = 3,
    ),





}