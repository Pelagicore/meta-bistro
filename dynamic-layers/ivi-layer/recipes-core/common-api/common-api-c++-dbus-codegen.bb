#
#   Copyright (C) 2015 Pelagicore AB
#
#   SPDX-License-Identifier: MIT
#
DESCRIPTION = "common-api-c++-native"
HOMEPAGE = "http://www.pelagicore.com"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"
PR = "r0"

DEPENDS = "common-api-c++-codegen common-api-dbus-integration"

inherit cmake maven-properties

GIT_REPO = "git://github.com/Pelagicore/common-api-dbus-tools.git;protocol=https"
S = "${WORKDIR}/git"

#EXTRA_OECMAKE += " -DMAVEN_OPTIONS=${MAVEN_OPTIONS}"

SRC_URI = "${GIT_REPO};branch=master"
SRCREV = "fefb5e2f7e87ce01a5c53858fc0ba4f052cc4035"
PV = "3.1.5.${SRCREV}"

FILES_${PN} += "${datadir}/* \
${libdir}/cmake/* \
"

BBCLASSEXTEND = "native nativesdk"
