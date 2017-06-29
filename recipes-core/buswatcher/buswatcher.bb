#
#   Copyright (C) 2015 Pelagicore AB
#
#   SPDX-License-Identifier: MIT
#
DESCRIPTION = "buswatcher"
SECTION = "multimedia"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343"

DEPENDS += "glib-2.0"

SRC_URI = " \
    file://watcher.c \
    file://CMakeLists.txt \
    file://COPYING \
"
S = "${WORKDIR}/"

inherit cmake

