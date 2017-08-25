package rodrigo.zaniolo.myweatherapp.utils;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static rodrigo.zaniolo.myweatherapp.utils.FontUtils.FontName.BLACK;
import static rodrigo.zaniolo.myweatherapp.utils.FontUtils.FontName.BOLD;
import static rodrigo.zaniolo.myweatherapp.utils.FontUtils.FontName.EXTRABOLD;
import static rodrigo.zaniolo.myweatherapp.utils.FontUtils.FontName.LIGHT;
import static rodrigo.zaniolo.myweatherapp.utils.FontUtils.FontName.MEDIUM;
import static rodrigo.zaniolo.myweatherapp.utils.FontUtils.FontName.REGULAR;
import static rodrigo.zaniolo.myweatherapp.utils.FontUtils.FontName.THIN;

/**
 * Created by rrzaniolo on 24/08/17.
 */

public class FontUtils {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({BLACK, BOLD, EXTRABOLD, LIGHT, MEDIUM, REGULAR, THIN})
    public @interface FontName {
        String BLACK = "fonts/Heebo-Black.ttf";
        String BOLD = "fonts/Heebo-Bold.ttf";
        String EXTRABOLD = "fonts/Heebo-ExtraBold.ttf";
        String LIGHT = "fonts/Heebo-Light.ttf";
        String MEDIUM = "fonts/Heebo-Medium.ttf";
        String REGULAR = "fonts/Heebo-Regular.ttf";
        String THIN = "fonts/Heebo-Thin.ttf";
    }
}
