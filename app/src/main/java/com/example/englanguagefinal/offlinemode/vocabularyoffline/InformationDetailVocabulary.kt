package com.example.englanguagefinal.offlinemode.vocabularyoffline

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.speech.tts.TextToSpeech
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.englanguagefinal.R
import com.example.englanguagefinal.model.vocabulary.SuccessVocabulary

class InformationDetailVocabulary(
    private val textWord: String,
    private val position: Int,
    private val context: Context,
    private val mListVocabulary: MutableList<SuccessVocabulary>,
    private var mTTS: TextToSpeech) {
    fun informationDetailVocabulary() {
        if (textWord == "Determine") {
            val speech = "/di'tə:min/"
            val translate = context.getString(R.string.determine)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Establish") {
            val speech = "/is'tæbliʃ/"
            val translate = context.getString(R.string.establish)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Engage") {
            val speech = "/in'geidʤ/"
            val translate = context.getString(R.string.engage)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Abide by") {
            val speech = "/ə'baid/"
            val translate = context.getString(R.string.abideby)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Assurance") {
            val speech = "/ə'ʃuərəns/"
            val translate = context.getString(R.string.assurance)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Cancellation") {
            val speech = "/,kænse'leiʃn/"
            val translate = context.getString(R.string.cancellation)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Agreement") {
            val speech = "/ə'gri:mənt/"
            val translate = context.getString(R.string.agreement)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Satisfaction") {
            val speech = "/,sætis'fækʃn/"
            val translate = context.getString(R.string.satisfaction)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Productive") {
            val speech = "/prəˈdʌktɪv/"
            val translate = context.getString(R.string.productive)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Persuasion") {
            val speech = "/pə'sweiʤn/"
            val translate: String = context.getString(R.string.persuasion)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Market") {
            val speech = "/'mɑ:kit/"
            val translate: String = context.getString(R.string.market)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Inspiration") {
            val speech = "/,inspə'reiʃn/"
            val translate: String = context.getString(R.string.inspiration)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Fad") {
            val speech = "/fæd/"
            val translate = context.getString(R.string.fad)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Currently") {
            val speech = "/ˈkʌrəntli/"
            val translate = context.getString(R.string.currently)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Convince") {
            val speech = "/kən'vins/"
            val translate: String = context.getString(R.string.convince)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Consume") {
            val speech = "/kən'sju:m/"
            val translate = context.getString(R.string.consume)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Competition") {
            val speech = "/,kɔmpi'tiʃn/"
            val translate = context.getString(R.string.competition)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Compare") {
            val speech = "/kəm'peə/"
            val translate = context.getString(R.string.compare)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Attract") {
            val speech = "/ə'trækt/"
            val translate = context.getString(R.string.attract)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Resolve") {
            val speech = "/ri'zɔlv/"
            val translate = context.getString(R.string.resolve)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Specific") {
            val speech = "/spi'sifik/"
            val translate = context.getString(R.string.specific)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Provision") {
            val speech = "/provision/"
            val translate = context.getString(R.string.provision)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Characteristic") {
            val speech = "/,kæriktə'ristik/"
            val translate = context.getString(R.string.characteristic)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Consequence") {
            val speech = "/'kɔnsikwəns/"
            val translate = context.getString(R.string.consequence)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Consider") {
            val speech = "/kən'sidə/"
            val translate = context.getString(R.string.consider)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Cover") {
            val speech = "/'kʌvə/"
            val translate = context.getString(R.string.cover)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Expiration") {
            val speech = "/,ekspaiə'reiʃn/"
            val translate = context.getString(R.string.expiration)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Frequently") {
            val speech = "/ˈfriːkwəntli/"
            val translate = context.getString(R.string.frequently)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Imply") {
            val speech = "/im'plai/"
            val translate = context.getString(R.string.imply)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Protect") {
            val speech = "/protect/"
            val translate = context.getString(R.string.protect)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Promise") {
            val speech = "/promise/"
            val translate = context.getString(R.string.Promise)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Reputation") {
            val speech = "/,repju:'teiʃn/"
            val translate = context.getString(R.string.Reputation)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Require") {
            val speech = "/ri'kwaiə/"
            val translate = context.getString(R.string.Require)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Variety") {
            val speech = "/və'raiəti/"
            val translate = context.getString(R.string.variety)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Address") {
            val speech = "/ə'dres/"
            val translate = "Kế hoạch kinh doanh của Marco nhằm vào nhu cầu của những chủ doanh nghiệp nhỏ"
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Avoid") {
            val speech = "/ə'vɔid/"
            val translate = "Nhằm tránh lụn bại việc làm ăn, những người chủ nên chuẩn bị một kế hoạch KD phù hợp"
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Demonstrate") {
            val speech = "/'demənstreit/"
            val translate = "Vị giáo sư đã chứng minh thông qua bài học tình huống là một kế hoạch kinh doanh có thể gây ấn tượng với một người cho vay"
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Develop") {
            val speech = "/di'veləp/"
            val translate = "Lily đã phát triển ý tưởng của cô ta vào kế hoạch kinh doanh bằng cách tham dự một lớp học tại trường cao đẳng cộng đồng"
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Evaluate") {
            val speech = "/i'væljueit/"
            val translate = "Đánh giá sức cạnh tranh của bạn là việc quan trọng khi lập một kế hoạch kinh doanh"
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Gather") {
            val speech = "/'gæðə/"
            val translate = context.getString(R.string.Gather)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Substitution") {
            val speech = "/,sʌbsti'tju:ʃn/"
            val translate = context.getString(R.string.Substitution)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Offer") {
            val speech = "/'ɔfə/"
            val translate = context.getString(R.string.offer)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Primarily") {
            val speech = "/'praimərili/"
            val translate = context.getString(R.string.primarily)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Risk") {
            val speech = "/rɪsk/"
            val translate = context.getString(R.string.risk)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Strategy") {
            val speech = "/ˈstrætədʒi/"
            val translate = context.getString(R.string.strategy)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Strong") {
            val speech = "/strɔɳ/"
            val translate = context.getString(R.string.strong)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Accommodate") {
            val speech = "/ə'kɔmədeit/"
            val translate = context.getString(R.string.accommodate)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Arrangement") {
            val speech = "/ə'reindʤmənt/"
            val translate = context.getString(R.string.arrangement)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Association") {
            val speech = "/ə,sousi'eiʃn/"
            val translate = context.getString(R.string.association)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Attend") {
            val speech = "/ə'tend/"
            val translate = context.getString(R.string.attend)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "get in touch") {
            val speech = "N/A"
            val translate = context.getString(R.string.getintouch)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "hold") {
            val speech = "/hould/"
            val translate = context.getString(R.string.hold)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "take part in") {
            val speech = "N/A"
            val translate = context.getString(R.string.takepartin)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Location") {
            val speech = "/lou'keiʃn/"
            val translate = context.getString(R.string.location)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Overcrowded") {
            val speech = "/əʊvəˈkraʊdɪd/"
            val translate = context.getString(R.string.overcrowded)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Register") {
            val speech = "/'redʤistə/"
            val translate = context.getString(R.string.register)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Select") {
            val speech = "/si'lekt/"
            val translate = context.getString(R.string.select)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Session") {
            val speech = "/'seʃn/"
            val translate = context.getString(R.string.session)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "access") {
            val speech = "/ˈækses/"
            val translate = context.getString(R.string.access)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "allocate") {
            val speech = "/ˈæləkeɪt/"
            val translate = context.getString(R.string.allocate)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Compatible") {
            val speech = "/kəm'pætəbl/"
            val translate = context.getString(R.string.compatible)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Delete") {
            val speech = "/di'li:t/"
            val translate = context.getString(R.string.delete)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Display") {
            val speech = "/dis'plei/"
            val translate = context.getString(R.string.display)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Duplicate") {
            val speech = "/'dju:plikit/"
            val translate = context.getString(R.string.duplicate)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Failure") {
            val speech = "/'feiljə/"
            val translate = context.getString(R.string.failure)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Figure out") {
            val speech = "/ˈfɪɡə/ /aʊt/"
            val translate = context.getString(R.string.figureout)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Ignore") {
            val speech = "/ig'nɔ:/"
            val translate = context.getString(R.string.ignore)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Search") {
            val speech = "/sə:tʃ/"
            val translate = context.getString(R.string.search)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Shut down") {
            val speech = "/ʃʌt/ /daʊn/"
            val translate = context.getString(R.string.shutdown)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "Warning") {
            val speech = "/'wɔ:niɳ/"
            val translate = context.getString(R.string.warning)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "disk") {
            val speech = "/disk/"
            val translate = context.getString(R.string.disk)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "facilitate") {
            val speech = "/fə'siliteit/"
            val translate = context.getString(R.string.facilitate)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "network") {
            val speech = "/'netwə:k/"
            val translate = context.getString(R.string.network)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "popularity") {
            val speech = "/,pɔpju'læriti/"
            val translate = context.getString(R.string.popularity)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "process") {
            val speech = "/ˈprəʊses/"
            val translate = context.getString(R.string.process)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "replace") {
            val speech = "/ri'pleis/"
            val translate = context.getString(R.string.replace)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "revolution") {
            val speech = "/,revə'lu:ʃn/"
            val translate = context.getString(R.string.revolution)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "sharp") {
            val speech = "/ʃɑ:p/"
            val translate = context.getString(R.string.sharp)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "skill") {
            val speech = "/skil/"
            val translate = context.getString(R.string.skill)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "software") {
            val speech = "/ˈsɒftweə(r)/"
            val translate = context.getString(R.string.software)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "store") {
            val speech = "to keep"
            val translate = context.getString(R.string.store)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "technically") {
            val speech = "/ˈteknɪkli/"
            val translate = context.getString(R.string.technically)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "appreciation") {
            val speech = "/ə,pri:ʃi'eiʃn/"
            val translate = context.getString(R.string.appreciation)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "be made of") {
            val speech = "N/A"
            val translate = context.getString(R.string.bemadeof)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "bring in") {
            val speech = "N/A"
            val translate = context.getString(R.string.bringin)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "casually") {
            val speech = "/ˈkæʒuəli/"
            val translate = context.getString(R.string.casually)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "code") {
            val speech = "/koud/"
            val translate = context.getString(R.string.code)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "expose") {
            val speech = "/iks'pouz/"
            val translate = context.getString(R.string.expose)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "glimpse") {
            val speech = "/glimps/"
            val translate = context.getString(R.string.glimpse)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "out of") {
            val speech = "N/A"
            val translate = context.getString(R.string.outof)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "outdated") {
            val speech = "/aut'deitid/"
            val translate = context.getString(R.string.outdated)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "practice") {
            val speech = "/'præktis/"
            val translate = context.getString(R.string.practice)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "reinforce") {
            val speech = "/,ri:in'fɔ:s/"
            val translate = context.getString(R.string.reinforce)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "verbally") {
            val speech = "/'və:bəli/"
            val translate = context.getString(R.string.verbally)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "affordable") {
            val speech = "/əˈfɔː.də.bəl/"
            val translate = context.getString(R.string.affordable)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "as needed") {
            val speech = "N/A"
            val translate = context.getString(R.string.asneeded)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "be in charge of") {
            val speech = "N/A"
            val translate = context.getString(R.string.beinchargeof)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "capacity") {
            val speech = "/kə'pæsiti/"
            val translate = context.getString(R.string.capacity)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "durable") {
            val speech = "/ˈdjʊərəbl/"
            val translate = context.getString(R.string.durable)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "initiative") {
            val speech = "/i'niʃiətiv/"
            val translate = context.getString(R.string.initiative)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "physically") {
            val speech = "/ˈfɪzɪkli /"
            val translate = context.getString(R.string.physically)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "provider") {
            val speech = "/provider/"
            val translate = context.getString(R.string.provider)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "recur") {
            val speech = "/ri'kə:/"
            val translate = context.getString(R.string.recur)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "reduction") {
            val speech = "/ri'dʌkʃn/"
            val translate = context.getString(R.string.reduction)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "stay on top of") {
            val speech = "N/A"
            val translate = context.getString(R.string.stayontopof)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
        if (textWord == "stock") {
            val speech = "/stɒk/"
            val translate = context.getString(R.string.stock)
            openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
        }
    }

    private fun openDialogShowVocabulary(gravity: Int, position: Int, speech: String, translate: String) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_view_xemchitiet)
        val window = dialog.window ?: return
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes = window.attributes
        windowAttributes.gravity = gravity
        window.attributes = windowAttributes
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true)
        } else {
            dialog.setCancelable(false)
        }
        val wordDialog = dialog.findViewById<TextView>(R.id.wordDialog)
        val speechDialog = dialog.findViewById<TextView>(R.id.speechDialog)
        val meanDialog = dialog.findViewById<TextView>(R.id.meanDialog)
        val exampleDialog = dialog.findViewById<TextView>(R.id.exampleDialog)
        val translateDialog = dialog.findViewById<TextView>(R.id.translateDialog)
        val imgVolumeDialog = dialog.findViewById<ImageView>(R.id.imgVolumeDialog)
        val btnConFirmDialog: Button = dialog.findViewById(R.id.btnConFirmDialog)
        val word: String = mListVocabulary[position].word
        val mean: String = mListVocabulary[position].mean
        val example: String = mListVocabulary[position].example
        wordDialog.text = word
        speechDialog.text = speech
        meanDialog.text = mean
        exampleDialog.text = example
        translateDialog.text = translate

        btnConFirmDialog.setOnClickListener {
            dialog.dismiss()
        }

        imgVolumeDialog.setOnClickListener {
            speak(word)
        }
        dialog.show()
    }

    fun speak(word: String) {
        val text: String = word
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}