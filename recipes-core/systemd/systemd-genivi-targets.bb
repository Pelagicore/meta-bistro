DESCRIPTION = "Systemd targets used by GENIVI Lifecycle components"
LICENSE = "GPLv2"

SRC_URI = " \
	file://lazy.target \
	file://focussed.target\
	file://unfocussed.target \
"

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
