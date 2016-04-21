#
#   Copyright (C) 2015 Pelagicore AB
#

LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad"

inherit cmake

SRC_URI = "git://github.com/Pelagicore/common-api-dbus-integration.git;protocol=https;branch=master"

SRCREV = "58400e553f81e5397015e142cfe5854a965781a4"

S = "${WORKDIR}/git"

FILES_${PN}-dev += "${libdir}/cmake/*"

ALLOW_EMPTY_${PN} = "1"

BBCLASSEXTEND = "native nativesdk"
