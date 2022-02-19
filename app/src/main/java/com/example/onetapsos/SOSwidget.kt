package com.example.onetapsos

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class SOSwidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val i1 : PendingIntent = Intent(context,MainActivity::class.java).let { intent ->
        intent.putExtra("SOS","Widget")
        PendingIntent.getActivity(context, 0, intent, 0)

    }


    val views = RemoteViews(context.packageName, R.layout.s_o_swidget)
        .apply{setOnClickPendingIntent(R.id.sos_btn,i1)}
    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}