package com.weather.ItemUI

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Room
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Composable
fun SearchItem(title : String = "Talwandi mange khan zira ferozepur punjab", state : String = "Punjab", country : String  = "India", onClick : () -> Unit = {}){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
//        border = BorderStroke(1.dp, Color.LightGray),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Icon(
                modifier = Modifier
                    .weight(1f),
                imageVector = Icons.Filled.Room,
                contentDescription = "locationIcon"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(8f)
            ) {
                Text(
                    text = title,
                    modifier = Modifier,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$state,$country",
                    modifier = Modifier,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            // This Feature is will be Edit after completion
//            Spacer(modifier = Modifier.width(8.dp))
//
//            IconButton(
//                modifier = Modifier.weight(1f),
//                onClick = {
//                    onClickIcon(title)
//                }
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.ArrowUpward,
//                    contentDescription = "locationIcon"
//                )
//            }


        }
    }

}