DESCRIPTION = "ivi-logging"
HOMEPAGE = "http://www.pelagicore.com"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad"
PR = "r0"

DEPENDS = "glib-2.0"

inherit cmake

# Make DLT support optional
# To enable, create a .bbappend with PACKAGECONFIG_append = "dlt"
# in your project layer
PACKAGECONFIG ??= ""
PACKAGECONFIG[dlt] = "-DENABLE_DLT_BACKEND=ON,,dlt-daemon,"

GIT_REPO = "git://github.com/Pelagicore/ivi-logging.git;protocol=https"
S = "${WORKDIR}/git"

FILES_${PN}-dev += " \
       ${libdir}/cmake/ \
       ${docdir}/ \
       "
