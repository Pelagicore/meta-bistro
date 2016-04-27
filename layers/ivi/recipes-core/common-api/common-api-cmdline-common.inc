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
SRCREV = "1fc82a742244fba83c0ef1695d5d45a0af17b3e5"
PV = "0.1+git${SRCREV}"

