#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = "common-api-c++-native"
HOMEPAGE = "http://www.pelagicore.com"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"
PR = "r0"

DEPENDS = "common-api-cmdline-codegen"

inherit cmake maven-properties

GIT_REPO = "git://github.com/Pelagicore/common-api-tools.git;protocol=https"
S = "${WORKDIR}/git"

SRC_URI = "${GIT_REPO};branch=master"
SRCREV = "81fe6698a95ba048ed2dbf1b557c884a97f939d6"
PV = "0.1+git${SRCREV}"

FILES_${PN} += "${datadir}/*"
ALLOW_EMPTY_${PN} = "1"

BBCLASSEXTEND = "native nativesdk"
