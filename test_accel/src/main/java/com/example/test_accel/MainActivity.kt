package com.example.test_accel

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.caverock.androidsvg.SVG
import com.example.test_accel.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var map: MapModel
    private lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        //map = intent.getSerializableExtra("map") as MapModel
        val svg_str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<!-- Created with Inkscape (http://www.inkscape.org/) -->\n" +
                "\n" +
                "<svg\n" +
                "   width=\"400\"\n" +
                "   height=\"200\"\n" +
                "   viewBox=\"0 0 105.83333 52.916666\"\n" +
                "   version=\"1.1\"\n" +
                "   id=\"svg5\"\n" +
                "   inkscape:version=\"1.1.2 (0a00cf5339, 2022-02-04)\"\n" +
                "   sodipodi:docname=\"test1.svg\"\n" +
                "   xmlns:inkscape=\"http://www.inkscape.org/namespaces/inkscape\"\n" +
                "   xmlns:sodipodi=\"http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd\"\n" +
                "   xmlns=\"http://www.w3.org/2000/svg\"\n" +
                "   xmlns:svg=\"http://www.w3.org/2000/svg\">\n" +
                "  <sodipodi:namedview\n" +
                "     id=\"namedview7\"\n" +
                "     pagecolor=\"#ffffff\"\n" +
                "     bordercolor=\"#666666\"\n" +
                "     borderopacity=\"0.0\"\n" +
                "     inkscape:pageshadow=\"0\"\n" +
                "     inkscape:pageopacity=\"0.0\"\n" +
                "     inkscape:pagecheckerboard=\"0\"\n" +
                "     inkscape:document-units=\"px\"\n" +
                "     showgrid=\"true\"\n" +
                "     inkscape:object-paths=\"false\"\n" +
                "     inkscape:snap-global=\"false\"\n" +
                "     inkscape:zoom=\"1.9117761\"\n" +
                "     inkscape:cx=\"201.12188\"\n" +
                "     inkscape:cy=\"163.46056\"\n" +
                "     inkscape:window-width=\"1920\"\n" +
                "     inkscape:window-height=\"1016\"\n" +
                "     inkscape:window-x=\"0\"\n" +
                "     inkscape:window-y=\"0\"\n" +
                "     inkscape:window-maximized=\"1\"\n" +
                "     inkscape:current-layer=\"g50\"\n" +
                "     units=\"px\"\n" +
                "     width=\"500px\" />\n" +
                "  <defs\n" +
                "     id=\"defs2\">\n" +
                "    <clipPath\n" +
                "       clipPathUnits=\"userSpaceOnUse\"\n" +
                "       id=\"clipPath732\">\n" +
                "      <rect\n" +
                "         style=\"fill:#0000ff;fill-rule:evenodd;stroke-width:0.264583\"\n" +
                "         id=\"rect734\"\n" +
                "         width=\"100\"\n" +
                "         height=\"40\"\n" +
                "         x=\"50.112305\"\n" +
                "         y=\"22.278006\" />\n" +
                "    </clipPath>\n" +
                "  </defs>\n" +
                "  <g\n" +
                "     inkscape:label=\"Layer 1\"\n" +
                "     inkscape:groupmode=\"layer\"\n" +
                "     id=\"layer1\">\n" +
                "    <g\n" +
                "       id=\"g50\">\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.565;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"M 1.,6 V 46 H 105. V 6 H 1.\"\n" +
                "         id=\"path1779\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"M 23.13106,5.9272983 V 26.927298 h 2 -5\"\n" +
                "         id=\"path1783\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"M 9.1310597,5.9272983 V 26.927298 H 15.13106 6.1310597\"\n" +
                "         id=\"path233\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"M 32.13106,5.9272983 V 26.927298 h -3 7\"\n" +
                "         id=\"path1785\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"M 44.13106,5.9272983 V 26.927298 h 3 -7\"\n" +
                "         id=\"path212\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"M 53.63106,5.9272983 V 26.927298 h 3 -5\"\n" +
                "         id=\"path1787\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"M 64.13106,5.9272983 V 26.927298 h 3 -6\"\n" +
                "         id=\"path1789\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"M 74.13106,5.9272983 V 15.927298\"\n" +
                "         id=\"path1791\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"m 74.13106,20.927298 v 6 h 4 -6\"\n" +
                "         id=\"path1793\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"M 93.13106,5.9272983 V 15.927298\"\n" +
                "         id=\"path1795\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"m 83.13106,26.927298 h 22\"\n" +
                "         id=\"path1797\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"m 93.13106,20.927298 v 6\"\n" +
                "         id=\"path1799\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"m 52.13106,32.927298 v 13\"\n" +
                "         id=\"path1801\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"M 45.260152,32.927298 H 72.13106\"\n" +
                "         id=\"path1803\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"m 76.73606,32.927298 h 11\"\n" +
                "         id=\"path1807\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"m 85.63106,33.122398 v 12.8049\"\n" +
                "         id=\"path1809\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"m 93.13106,37.427298 v 8.5\"\n" +
                "         id=\"path1811\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"m 91.13106,32.927298 h 14\"\n" +
                "         id=\"path1813\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"M 1.1310597,32.927298 H 26.13106\"\n" +
                "         id=\"path2008\" />\n" +
                "      <path\n" +
                "         style=\"fill:none;stroke:#000000;stroke-width:0.3;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\n" +
                "         d=\"m 32.13106,36.927298 c 0,8.849315 0,8.849315 0,8.849315\"\n" +
                "         id=\"path2012\" />\n" +
                "    </g>\n" +
                "  </g>\n" +

                "</svg>\n"
        map = MapModel("test", "", Info(), svg_str)
        //Log.i("tag", "MainActivity")
        //Log.i("tag", map.toString())
        bind.mapName.text = map.name
        bind.mapView.setSvg(map.image)
        //bind.mapView.setSvg(resources.getString(R.string.test_square))


        // Привязка масштабирования к кнопкам
        bind.addButton.setOnClickListener{
            bind.mapView.dec()
        }

        bind.subButton.setOnClickListener{
            bind.mapView.inc()
        }

    }
}