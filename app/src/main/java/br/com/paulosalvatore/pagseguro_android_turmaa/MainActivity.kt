package br.com.paulosalvatore.pagseguro_android_turmaa

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.androidplot.util.PixelUtils
import com.androidplot.xy.*
import com.google.common.base.Joiner
import kotlinx.android.synthetic.main.activity_main.*
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Apenas um exemplo de utilização do Joiner do Guava
        val joiner = Joiner.on(" ").skipNulls()
        textView.text = joiner.join("Android", null, "Kotlin", "ProGuard")

        val plot = findViewById<View>(R.id.plot) as XYPlot

        val domainLabels = listOf<Number>(1, 2, 3, 6, 7, 8, 9, 10, 13, 14)
        val series1Numbers = listOf<Number>(1, 4, 2, 8, 4, 16, 8, 32, 16, 64)
        val series2Numbers = listOf<Number>(5, 2, 10, 5, 20, 10, 40, 20, 80, 40)

        val series1 = SimpleXYSeries(series1Numbers, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series1")
        val series2 = SimpleXYSeries(series2Numbers, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2")

//        val series1Format = LineAndPointFormatter(Color.BLACK, Color.WHITE, Color.CYAN, null)
//        val series2Format = LineAndPointFormatter(Color.BLACK, Color.WHITE, Color.CYAN, null)
        val series1Format = LineAndPointFormatter(this, R.xml.line_point_formatter_with_labels)
        val series2Format = LineAndPointFormatter(this, R.xml.line_point_formatter_with_labels_2)

        series2Format.linePaint.pathEffect =
            DashPathEffect(floatArrayOf(PixelUtils.dpToPix(20f), PixelUtils.dpToPix(15f)), 0f)

        series1Format.interpolationParams = CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal)
        series2Format.interpolationParams = CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal)

        plot.addSeries(series1, series1Format)
        plot.addSeries(series2, series2Format)

        plot.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).format = object : Format() {
            override fun format(obj: Any, toAppendTo: StringBuffer, pos: FieldPosition): StringBuffer {
                val i = Math.round((obj as Number).toFloat())
                return toAppendTo.append(domainLabels[i])
            }

            override fun parseObject(source: String, pos: ParsePosition): Any? {
                return null
            }
        }
    }
}
