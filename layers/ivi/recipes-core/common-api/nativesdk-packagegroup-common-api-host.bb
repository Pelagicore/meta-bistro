#
#   Copyright (C) 2015 Pelagicore AB
#
SUMMARY = "Host packages for Common API code generation"
PR = "r12"
LICENSE = "MIT"

inherit packagegroup nativesdk

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS_${PN} = "\
    nativesdk-common-api-cmdline-codegen \
    nativesdk-common-api-c++-dbus-codegen \
    "
 
