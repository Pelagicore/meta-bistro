#
#   Copyright (C) 2015 Pelagicore AB
#

DESCRIPTION = "common-api-cmdline"
HOMEPAGE = "http://www.pelagicore.com"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad"
PR = "r0"

GIT_REPO = "git://github.com/Pelagicore/common-api-cmdline.git;protocol=https"

SRC_URI = "${GIT_REPO};branch=master"
SRCREV = "74c695a0c70a7cf32a69b725baccaa4fd88846c4"
PV = "0.1+git${SRCREV}"

