package by.darya_zdzitavetskaya.bstu_canteen.utils.custom_views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import by.darya_zdzitavetskaya.bstu_canteen.R

@SuppressLint("AppCompatCustomView")
class PinEntryEditText : EditText {

    private var mSpace = 24f //24 dp by default, space between the lines
    private var mCharSize: Float = 0.toFloat()
    private var mNumChars = 4f
    private var mLineSpacing = 8f //8dp by default, height of the text from our lines
    private var mMaxLength = 4

    private var mClickListener: OnClickListener? = null
    protected var mOnPinEnteredListener: OnPinEnteredListener? = null

    private var mPointRadius = 4f //4dp by default
    private var mLinesPaint: Paint? = null
    internal val mStates = arrayOf(
        intArrayOf(android.R.attr.state_selected), // selected
        intArrayOf(android.R.attr.state_focused), // focused
        intArrayOf(-android.R.attr.state_focused)
    )// unfocused

    private val mColors = intArrayOf(Color.GREEN, Color.BLACK, Color.GRAY)

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val multi = context.resources.displayMetrics.density

        mSpace *= multi //convert to pixels for our density
        mLineSpacing *= multi //convert to pixels for our density
        mPointRadius *= multi
        mLinesPaint = Paint(paint)

        if (!isInEditMode) {
            val outValue = TypedValue()
            context.theme.resolveAttribute(
                R.attr.colorControlActivated,
                outValue, true
            )
            val colorActivated = outValue.data
            mColors[0] = colorActivated

            context.theme.resolveAttribute(
                R.attr.colorPrimaryDark,
                outValue, true
            )
            val colorDark = outValue.data
            mColors[1] = colorDark

            context.theme.resolveAttribute(
                R.attr.colorControlHighlight,
                outValue, true
            )
            val colorHighlight = outValue.data
            mColors[2] = colorHighlight
        }

        setBackgroundResource(0)
        mMaxLength = attrs.getAttributeIntValue(XML_NAMESPACE, "maxLength", DEF_MAX_LENGTH)
        mNumChars = mMaxLength.toFloat()
        mLinesPaint!!.color = ContextCompat.getColor(context, R.color.colorGrey)

        //Disable copy paste
        super.setCustomSelectionActionModeCallback(object : ActionMode.Callback {
            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode) {}

            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                return false
            }
        })
        // When tapped, move cursor to end of text.
        super.setOnClickListener { v ->
            this@PinEntryEditText.setSelection(this@PinEntryEditText.text.length)
            if (mClickListener != null) {
                mClickListener!!.onClick(v)
            }
        }

    }

    override fun setOnClickListener(l: OnClickListener?) {
        mClickListener = l
    }

    override fun setCustomSelectionActionModeCallback(actionModeCallback: ActionMode.Callback) {
        throw RuntimeException("setCustomSelectionActionModeCallback() not supported.")
    }

    override fun onDraw(canvas: Canvas) {
        //super.onDraw(canvas);
        val availableWidth = width - paddingRight - paddingLeft
        mCharSize = if (mSpace < 0) {
            availableWidth / (mNumChars * 2 - 1)
        } else {
            (availableWidth - mSpace * (mNumChars - 1)) / mNumChars
        }

        var startX = paddingLeft
        val bottom = height - paddingBottom
        val middleHeight = (paddingTop + (height - paddingBottom) / 2).toFloat()

        //Text Width
        val text = text
        val textLength = text.length
        val textWidths = FloatArray(textLength)
        paint.getTextWidths(getText(), 0, textLength, textWidths)

        var i = 0
        while (i < mNumChars) {
            val middle = startX + mCharSize / 2

            if (getText().length > i) {
                canvas.drawText(
                    text,
                    i,
                    i + 1,
                    middle - textWidths[0] / 2,
                    bottom - mLineSpacing,
                    paint
                )
            } else {
                canvas.drawCircle(middle, middleHeight, mPointRadius, mLinesPaint!!)
            }

            startX += if (mSpace < 0) {
                (mCharSize * 2).toInt()
            } else {
                (mCharSize + mSpace).toInt()
            }
            i++
        }
    }

    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if (mOnPinEnteredListener != null && text.length == mMaxLength) {
            mOnPinEnteredListener!!.onPinEntered(this.rootView, text)
        }
    }

    fun setOnPinEnteredListener(l: OnPinEnteredListener) {
        mOnPinEnteredListener = l
    }

    interface OnPinEnteredListener {
        fun onPinEntered(view: View, str: CharSequence)
    }

    companion object {
        const val XML_NAMESPACE = "http://schemas.android.com/apk/res/android"
        const val DEF_MAX_LENGTH = 5
    }
}
