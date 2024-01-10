package com.example.wzker.azkar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AzkarNavigationItem(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    route: String,
    currentRoute: String,
    @StringRes title: Int,
    @DrawableRes icon: Int,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxHeight()
            .clickable {
                onClick(route)
            }
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = null,
            modifier = modifier.size(30.dp),
            tint = if (route == currentRoute) {
                LocalContentColor.current
            } else {
                LocalContentColor.current.copy(alpha = 0.5f)
            }
        )
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp),
            modifier = modifier.paddingFromBaseline(top = 10.dp),
            color = if (route == currentRoute) {
                LocalContentColor.current
            } else {
                LocalContentColor.current.copy(alpha = 0.5f)
            }
        )
    }
}
