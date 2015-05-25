FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Don't install their setup script since it's not used by our service file
do_install_append() {
	rm -rf ${D}${systemd_unitdir}/scripts
}
