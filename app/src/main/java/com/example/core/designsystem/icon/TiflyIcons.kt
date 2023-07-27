package com.example.core.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.tiflysignapp.R


object TiflyIcons {

    val Add = Icons.Rounded.Add
    val ArrowBack = Icons.Rounded.ArrowBack
    val Check = Icons.Rounded.Check
    val Close = Icons.Rounded.Close
    val Search = Icons.Rounded.Search
    val Settings = Icons.Rounded.Settings

    val Menu = Icons.Rounded.Menu
    val Book = R.drawable.sign_item_icon
    val Arrow = R.drawable.arrow_forward
    val Upcoming = R.drawable.ic_upcoming
    val UpcomingBorder = R.drawable.ic_upcoming_border
    val Bookmarks = R.drawable.ic_bookmarks
    val BookmarksBorder = R.drawable.ic_bookmarks_border
    val ChangeTheme = Icons.Rounded.Edit

    val NounCategory = R.drawable.noun_categories

    val Training = Icons.Rounded.PlayArrow




}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}