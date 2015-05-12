SECTION = "multimedia"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${PN}-${PV}/COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS += "gupnp"

SRC_URI = "https://01.org/sites/default/files/downloads/dleyna/${PN}-${PV}.tar.gz"

FILES_${PN} += " \
	${libdir}/${PN}/*.so.* \
	${sysconfdir} \
	${datadir} \
	"
FILES_${PN}-dev += "${libdir}/${PN}/*.so"
FILES_${PN}-dbg += "${libdir}/${PN}/.debug"

FILES_${PN} += "${libdir}/dleyna-1.0/connectors"
FILES_${PN}-dbg = "${libdir}/dleyna-1.0/connectors/.debug"

inherit autotools gettext
