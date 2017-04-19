import android.text.TextUtils
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.util.regex.Pattern

///////////////////////VIEW///////////////////
fun ImageView.load(url: String, holderResId: Int) {
    Picasso.with(context).load(url).placeholder(holderResId).into(this)
}

//////////////////////////CONVERT PRIMARY TYPE////////////////////////

/**
 * Checking an input string whether or not match a pattern
 */
fun String.checkMatchPattern(pattern: Pattern): Boolean {
    return pattern.matcher(this).matches()
}

/**
 * Checking an array of String whether or not have a null value
 */
fun Array<String>.isExistNull(): Boolean {
    return this.any { TextUtils.isEmpty(it) }
}

