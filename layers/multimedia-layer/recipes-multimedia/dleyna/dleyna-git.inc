SECTION = "multimedia"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS += "gupnp gupnp-dlna gupnp-av"

SRC_URI = "git://github.com/01org/${BPN}.git;protocol=https;branch=master"

S = "${WORKDIR}/git/"

PV = "0.5.0+git${SRCPV}"

FILES_${PN} += " \
	${libdir}/${PN}/*.so.* \
	${sysconfdir} \
	${datadir} \
	"
FILES_${PN}-dev += "${libdir}/${PN}/*.so"
FILES_${PN}-dbg += "${libdir}/${PN}/.debug"

FILES_${PN} += "${libdir}/dleyna-1.0/connectors"
FILES_${PN}-dbg = "${libdir}/dleyna-1.0/connectors/.debug"

inherit autotools gettext pkgconfig

