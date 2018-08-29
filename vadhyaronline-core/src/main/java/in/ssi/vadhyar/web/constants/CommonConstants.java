package in.ssi.vadhyar.web.constants;

public final class CommonConstants {

    public static final class RoleConstants {

        public static final int ADMIN_ROLE_ID = 1;

        public static final String ADMIN_ROLE_NAME = "Admin";

        public static final int VADHYAR_ROLE_ID = 2;

        public static final String VADHYAR_ROLE_NAME = "Vadhyar";

        public static final int ASSISTANT_ROLE_ID = 3;

        public static final String ASSISTANT_ROLE_NAME = "Assistant";

        public static final int USER_ROLE_ID = 4;

        public static final String USER_ROLE_NAME = "User";

    }

    public static final class StatusConstants {

        public static final int STATUS_ACTIVE_ID = 1;

        public static final String STATUS_ACTIVE_VALUE = "Active";

        public static final int STATUS_INACTIVE_ID = 2;

        public static final String STATUS_INACTIVE_VALUE = "Inactive";

        public static final int STATUS_LOGGED_IN_ID = 3;

        public static final String STATUS_LOGGED_IN_VALUE = "Logged in";

        public static final int STATUS_LOCKED_ID = 4;

        public static final String STATUS_LOCKED_VALUE = "Locked";

    }

    public static final class CacheConstants {

        public static final String LOGIN_USERS = "Login-Users";

        public static final String VEDA = "Cache-Veda";

        public static final String STATUS = "Cache-Status";

        public static final String ROLES = "Cache-Roles";

    }

    public static final class EventStatusConstants {

        public static final int EVENT_STATUS_DRAFT_ID = 1;

        public static final String EVENT_STATUS_DRAFT = "Draft";

        public static final int EVENT_STATUS_REQUESTED_ID = 2;

        public static final String EVENT_STATUS_REQUESTED = "Requested";

        public static final int EVENT_STATUS_ACCEPTED_ID = 3;

        public static final String EVENT_STATUS_ACCEPTED = "Accepted";

        public static final int EVENT_STATUS_CANCELLED_ID = 4;

        public static final String EVENT_STATUS_CANCELLED = "Cancelled";

        public static final int EVENT_STATUS_COMPLETED_ID = 5;

        public static final String EVENT_STATUS_COMPLETED = "Completed";
    }
}
