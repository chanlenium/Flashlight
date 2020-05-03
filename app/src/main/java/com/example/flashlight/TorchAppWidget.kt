package com.example.flashlight

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class TorchAppWidget : AppWidgetProvider() {

    // call onUndate() method whenever widget is needed to be updated
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) { // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) { // if the number of widget is more than one, all of the widgets are updated
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) { // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) { // Enter relevant functionality for when the last widget is disabled
    }

    companion object {  // perform following code when the widget is updated
        fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val widgetText: CharSequence = context.getString(R.string.appwidget_text)
            // Construct the RemoteViews object
            // 위젯은 activity에서 레이아웃을 다루는 것과는 방법이 다름. 위젯에 배치하는 뷰는 따로 있으며, 이것은 RemoteViews 객체로 가져올 수 있음
            val views = RemoteViews(context.packageName, R.layout.torch_app_widget)

            // change text value of RemoteView object
            views.setTextViewText(R.id.appwidget_text, widgetText)

            // Intent
            var intent = Intent(context, TorchService::class.java)

            // PendingIntent는 실행할 인텐트 정보를 가지고 있다가 수행하며, getService()는 서비스를 실행함
            val pendingIntent = PendingIntent.getService(context, 0, intent, 0)

            // If the widget is clicked, perform the Intent
            // 클릭 이벤트를 실행하려면 setOnClickPendingIntent() 사용(클릭이 발생할 뷰의 id와 PendingIntent객체 필요)
            views.setOnClickPendingIntent(R.id.appwidget_layout, pendingIntent)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}