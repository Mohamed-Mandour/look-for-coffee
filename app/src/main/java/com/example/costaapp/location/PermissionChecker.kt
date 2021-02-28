package com.example.costaapp.location

interface PermissionChecker {

    val hasAnyLocationPermissions: Boolean

    val hasFineLocationPermission: Boolean
}