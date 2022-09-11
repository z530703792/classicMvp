package com.classic.base_library.App;

import java.io.File;

/**
 * Created by zcq
 */

public class Constants {

    public static final String PATH_DATA  = App.getInstance().getCacheDir().getAbsolutePath()
            + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    /**
     * ------------------------------------------------------------------------------
     */

    // intent
    public static final String MODEL      = "model";
    public static final String TITLE_TYPE = "title_type";
    public static final String TYPE       = "type";
    //RecyclerView
    public static final String HEAD_DATA  = "data";

}
