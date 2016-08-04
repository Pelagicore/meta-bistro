#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = "dLeyna Server"
DEPENDS = "dleyna-core"
require dleyna.inc

EXTRA_OECONF += " --enable-never-quit "

SRC_URI[md5sum] = "31999e4d05c86ee46c24adb5b6b13c44"
SRC_URI[sha256sum] = "eab6c53194e573e19f189e4835a8fac8a9568a9822086e688207f6f33cb15be8"

