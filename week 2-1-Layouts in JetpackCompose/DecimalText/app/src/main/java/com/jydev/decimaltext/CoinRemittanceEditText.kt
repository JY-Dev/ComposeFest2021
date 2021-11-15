package com.jydev.decimaltext

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.DecimalFormat

/**
 * 최대 7자리 & 소수점 셋째짜리 까지
 * 0은 한번만 입력되게 처리
 */
class CoinRemittanceEditText @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    AppCompatEditText(context, attrs) {
    private val coinAmountRegex by lazy { """([0-9]|[1-9][0-9]{0,6})[.][0-9]{0,3}|[1-9][0-9]{0,6}|[0]""".toRegex() }
    private val _coinAmount = MutableLiveData<Float>()
    val coinAmount : LiveData<Float> = _coinAmount
    init {
        addTextChangedListener(object : TextWatcher {
            var preText = ""
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                preText = s.toString().removeComma()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val replaceString = s.toString().removeComma()
                if (preText == replaceString)
                    return
                if (replaceString.isEmpty()) {
                    setCoinAmountText("")
                    return
                }
                if (!coinAmountRegex.matches(replaceString)) {
                    val lastString = replaceString[replaceString.lastIndex].toString()
                    if(preText == "0" && (lastString!="."&&lastString.toInt() > 0)){
                        setCoinAmountText(lastString)
                    }

                    else{
                        setDecimalText(preText)
                    }

                } else {
                    setDecimalText(replaceString)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        // 복사 붙여넣기 disable 처리하기위해
        isLongClickable = false
        // 커서 disable
        isCursorVisible = false
    }

    private fun setCoinAmountText(target: String){
        setText(target)
        _coinAmount.value = getNumber()
    }

    private fun setDecimalText(target : String){
        val result = getDecimalText(target)
        setCoinAmountText(result)
        setSelection(result.length)
    }

    private fun getDecimalText(target: String): String {
        if (target.isEmpty()) return ""
        val decimalFormat = DecimalFormat("#,###").apply {
            maximumFractionDigits = 3
        }

        val splitDecimal = target.split(".")
        val number = splitDecimal[0].toInt()
        val decimalNumber = if (splitDecimal.size > 1) splitDecimal[1] else ""
        val decimalSymbol = if (splitDecimal.size > 1) "." else ""
        return decimalFormat.format(number) + decimalSymbol + decimalNumber
    }

    private fun getNumber() : Float{
        if(text.toString().isEmpty())
            return 0f
        return (text.toString()).removeComma().toFloat()
    }

    private fun String.removeComma() : String =
        replace(",","")

    fun plusNumber(number : Int){
        setText((getNumber()+number).toString())
    }
}