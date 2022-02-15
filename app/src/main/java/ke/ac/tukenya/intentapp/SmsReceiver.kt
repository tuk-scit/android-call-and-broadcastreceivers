package ke.ac.tukenya.intentapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log

class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action ==
            Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
                val messages = Telephony.Sms.Intents
                    .getMessagesFromIntent(intent)
                if (messages.isNotEmpty()) {
                    messages.forEach {
                        if (it != null) {
                            val from = it.originatingAddress
                            val msg = it.messageBody
                            Log.w("SmsReceiver", "onReceive: $from -> $msg")
                        }
                    }
                }
        }
    }
}