SUMMARY = "Adobe Source Sans Pro Fonts"
HOMEPAGE = "https://github.com/adobe-fonts/source-sans-pro"

SECTION = "fonts"
LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a8803491c87b02cf60c0d0459ba7b81b"

RO_V = "2.010R-ro"
IT_V = "1.065R-it"
PV = "${RO_V}-${IT_V}"

SRC_URI = "https://github.com/adobe-fonts/source-sans-pro/archive/${RO_V}/${IT_V}.tar.gz"
SRC_URI[md5sum] = "5dfa6f327cdd4cb363f8887493696a4c"
SRC_URI[sha256sum] = "e29660947d1a77bc2682b6036cfa4d644d9cdf00a272baaf60dda56e547c79e8"

S = "${WORKDIR}/source-sans-pro-${PV}"

do_install() {
    install -d ${D}${datadir}/fonts/ttf/
    for f in ${S}/TTF/*.ttf; do
        install -Dm0644 $f ${D}${datadir}/fonts/ttf/
    done
}

FILES_${PN} += "${datadir}/fonts/"

inherit fontcache
