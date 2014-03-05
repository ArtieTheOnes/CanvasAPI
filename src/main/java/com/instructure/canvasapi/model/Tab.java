package com.instructure.canvasapi.model;

import android.content.Context;
import com.instructure.canvasapi.utilities.APIHelpers;

import java.io.Serializable;

/**
 * Created by Joshua Dutton on 9/6/13.
 *
 * Copyright (c) 2014 Instructure. All rights reserved.
 */

public class Tab implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String TYPE_INTERNAL = "internal";
    public static final String TYPE_EXTERNAL = "external";

    // id constants (these should never change in the API)
    public static final String SYLLABUS_ID = "syllabus";
    public static final String AGENDA_ID = "agenda";
    public static final String ASSIGNMENTS_ID = "assignments";
    public static final String DISCUSSIONS_ID = "discussions";
    public static final String PAGES_ID = "pages";
    public static final String PEOPLE_ID = "people";
    public static final String QUIZZES_ID = "quizzes";
    public static final String FILES_ID = "files";
    public static final String ANNOUNCEMENTS_ID = "announcements";
    public static final String MODULES_ID = "modules";
    public static final String GRADES_ID = "grades";
    public static final String COLLABORATIONS_ID = "collaborations";
    public static final String CONFERENCES_ID = "conferences";
    public static final String OUTCOMES_ID = "outcomes";
    public static final String NOTIFICATIONS_ID = "notifications";
    public static final String HOME_ID = "home";
    public static final String CHAT_ID = "chat";
    public static final String SETTINGS_ID = "settings";

    public static final String[][] TAB_GROUPS = {
            {HOME_ID, GRADES_ID, NOTIFICATIONS_ID},
            {SYLLABUS_ID, AGENDA_ID, MODULES_ID},
            {ASSIGNMENTS_ID, QUIZZES_ID, PAGES_ID, FILES_ID},
            {ANNOUNCEMENTS_ID, COLLABORATIONS_ID, CONFERENCES_ID, PEOPLE_ID, DISCUSSIONS_ID, OUTCOMES_ID, CHAT_ID, SETTINGS_ID}
    };
    public static final int LTI_GROUP_INDEX = TAB_GROUPS.length;

    // API Variables
    private String id;
    private String label;
    private String type;
    private String html_url;    // internal url
    private String url;         // external url

    // helper variables
    private int groupId;

    ///////////////////////////////////////////////////////////////////////////
    // Getters & Setters
    ///////////////////////////////////////////////////////////////////////////

    public String getId() {
        return id;
    }
    public String getLabel() {
        return label;
    }
    public String getType() {
        return type;
    }
    public boolean isExternal() {
        return type.equals(TYPE_EXTERNAL);
    }
    public String getUrl(Context context) {
        String temp_html_url = html_url;

        //Domain strips off trailing slashes.
        if(!temp_html_url.startsWith("/")){
            temp_html_url = "/" + temp_html_url;
        }

        return APIHelpers.getDomain(context) + temp_html_url;
    }

    public String getExternalUrl() {
        return url;
    }
    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////

    private Tab () {}

    public static Tab newInstance(String id, String label) {
        Tab result = new Tab();
        result.id = id;
        result.label = label;
        result.type = TYPE_INTERNAL;
        return result;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tab tab = (Tab) o;

        if (!id.equals(tab.id)) return false;
        if (!label.equals(tab.label)) return false;

        return true;
    }
}
