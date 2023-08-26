package permissions

@Suppress("unused")
enum class MusicUserPermissions {
    CREATE_OWN,

    READ_OWN,
    READ_GROUP,
    READ_PUBLIC,
    READ_CANDIDATE,

    UPDATE_OWN,
    UPDATE_CANDIDATE,
    UPDATE_PUBLIC,

    CLOSE_OWN,
    CLOSE_CANDIDATE,
    CLOSE_PUBLIC,

    DELETE_OWN,
    DELETE_CANDIDATE,
    DELETE_PUBLIC,

    ALL_DISCUSSIONS_OWN,
    ALL_DISCUSSIONS_PUBLIC,
    ALL_DISCUSSIONS_CANDIDATE,

    USERS_DISCUSSIONS_OWN,
    USERS_DISCUSSIONS_PUBLIC,
    USERS_DISCUSSIONS_CANDIDATE,
}
