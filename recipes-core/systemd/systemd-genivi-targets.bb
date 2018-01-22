DESCRIPTION = "Systemd targets used by GENIVI Lifecycle components"
LICENSE = "GPLv2"

inherit allarch

SRC_URI = " \
	file://lazy.target \
	file://focussed.target\
	file://unfocussed.target \
	file://COPYING \
"

LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_populate_sysroot[noexec] = "1"

do_install() {
	install -Dm0644 ${WORKDIR}/lazy.target ${D}/lib/systemd/system/lazy.target
	install -Dm0644 ${WORKDIR}/focussed.target ${D}/lib/systemd/system/focussed.target
	install -Dm0644 ${WORKDIR}/unfocussed.target ${D}/lib/systemd/system/unfocussed.target
}

PACKAGES = "${PN}"
FILES_${PN} = "/lib/systemd"
