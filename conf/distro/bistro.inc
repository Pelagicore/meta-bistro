DISTRO = "bistro"
DISTRO_NAME = "Bistro 1.0"
DISTRO_VERSION = "1.5+snapshot-${DATE}"
SDK_VENDOR = "-bistrosdk"
SDK_VERSION := "${@'${DISTRO_VERSION}'.replace('snapshot-${DATE}','snapshot')}"

MAINTAINER = "Erik Botö <erik.boto@pelagicore.com>"

TARGET_VENDOR = "-bistro"

LOCALCONF_VERSION = "1"
LAYER_CONF_VERSION ?= "6"

# Following line is a copy from meta/conf/distro/include/default-distrovars.inc, but without 'x11'  (note: removing the 'x11' from the variable doesn't work due resulting to eglibc build errors)
DISTRO_FEATURES = "alsa argp bluetooth bluez5 ext2 irda largefile pcmcia usbgadget usbhost wifi xattr nfs pci 3g ${DISTRO_FEATURES_LIBC}"

VIRTUAL-RUNTIME_init_manager = "systemd"
VIRTUAL-RUNTIME_initscripts = " "
DISTRO_FEATURES =+ " largefile multiarch systemd ptest"
DISTRO_FEATURES_BACKFILL_CONSIDERED += "sysvinit"

PREFERRED_VERSION_gupnp ?= "0.20.12"
PREFERRED_VERSION_gssdp ?= "0.14.8"
PREFERRED_VERSION_gupnp-av ?= "0.12.4"

SDK_NAME = "${DISTRO}-${TCLIBC}-${SDK_ARCH}-${TARGET_ARCH}"
SDKPATH = "/opt/${DISTRO}/${SDK_VERSION}"

# Override these in bistro based distros to modify DISTRO_EXTRA_R*
BISTRO_DEFAULT_EXTRA_RDEPENDS = "packagegroup-core-boot"
BISTRO_DEFAULT_EXTRA_RRECOMMENDS = "kernel-module-af-packet"

DISTRO_EXTRA_RDEPENDS += " ${BISTRO_DEFAULT_EXTRA_RDEPENDS}"
DISTRO_EXTRA_RRECOMMENDS += " ${BISTRO_DEFAULT_EXTRA_RRECOMMENDS}"

POKYQEMUDEPS = "${@bb.utils.contains("INCOMPATIBLE_LICENSE", "GPLv3", "", "qemu-config",d)}"
DISTRO_EXTRA_RDEPENDS_append_qemuarm = " ${POKYQEMUDEPS}"
DISTRO_EXTRA_RDEPENDS_append_qemumips = " ${POKYQEMUDEPS}"
DISTRO_EXTRA_RDEPENDS_append_qemuppc = " ${POKYQEMUDEPS}"
DISTRO_EXTRA_RDEPENDS_append_qemux86 = " ${POKYQEMUDEPS}"
DISTRO_EXTRA_RDEPENDS_append_qemux86-64 = " ${POKYQEMUDEPS}"

TCLIBCAPPEND = ""

QEMU_TARGETS ?= "arm i386 mips mipsel ppc x86_64 aarch64"
# Other QEMU_TARGETS "mips64 mips64el sh4"

PREMIRRORS ?= "\
bzr://.*/.*   http://downloads.yoctoproject.org/mirror/sources/ \n \
cvs://.*/.*   http://downloads.yoctoproject.org/mirror/sources/ \n \
git://.*/.*   http://downloads.yoctoproject.org/mirror/sources/ \n \
hg://.*/.*    http://downloads.yoctoproject.org/mirror/sources/ \n \
osc://.*/.*   http://downloads.yoctoproject.org/mirror/sources/ \n \
p4://.*/.*    http://downloads.yoctoproject.org/mirror/sources/ \n \
svn://.*/.*   http://downloads.yoctoproject.org/mirror/sources/ \n"

MIRRORS =+ "\
ftp://.*/.*      http://downloads.yoctoproject.org/mirror/sources/ \n \
http://.*/.*     http://downloads.yoctoproject.org/mirror/sources/ \n \
https://.*/.*    http://downloads.yoctoproject.org/mirror/sources/ \n"

# The CONNECTIVITY_CHECK_URI's are used to test whether we can succesfully
# fetch from the network (and warn you if not). To disable the test set
# the variable to be empty.
# Git example url: git://git.yoctoproject.org/yocto-firewall-test;protocol=git;rev=HEAD

PREFERRED_PROVIDER_jpeg = "jpeg"
PREFERRED_PROVIDER_jpeg-native = "jpeg-native"

CONNECTIVITY_CHECK_URIS ?= " \
             https://eula-downloads.yoctoproject.org/index.php \
             http://bugzilla.yoctoproject.org/report.cgi"

SANITY_TESTED_DISTROS ?= " \
            CentOS-6.* \n \
            CentOS-7.* \n \
            CentOS release 5.6 (Final) \n \
            CentOS release 5.7 (Final) \n \
            CentOS release 5.8 (Final) \n \
            CentOS release 6.3 (Final) \n \
            Debian-7.* \n \
            Debian-8.* \n \
            Debian GNU/Linux 6.0.6 (squeeze) \n \
            Debian GNU/Linux 7.0 (wheezy) \n \
            Debian-unstable \n \
            Fedora-21 \n \
            Fedora release 16 (Verne) \n \
            Fedora release 17 (Beefy Miracle) \n \
            Fedora release 18 (Spherical Cow) \n \
            openSUSE 12.2 \n \
            openSUSE-13.2 \n \
            poky-1.7 \n \
            poky-1.8 \n \
            Poky 7.0 (Yocto Project 1.2 Reference Distro) 1.2 \n \
            Poky 8.0 (Yocto Project 1.3 Reference Distro) 1.3 \n \
            Ubuntu 10.04.4 LTS \n \
            Ubuntu 11.10 \n \
            Ubuntu 12.04.1 LTS \n \
            Ubuntu 12.04.2 LTS \n \
            Ubuntu 12.04 LTS \n \
            Ubuntu 12.10 \n \
            Ubuntu-14.04 \n \
            Ubuntu-14.10 \n \
            Ubuntu-15.04 \n \
            Yocto (Built by Poky 7.0) 1.2 \n \
            Yocto (Built by Poky 8.0) 1.2 \n \
	    "

# Default hash policy for distro
BB_SIGNATURE_HANDLER ?= 'OEBasicHash'
#
# OELAYOUT_ABI allows us to notify users when the format of TMPDIR changes in 
# an incompatible way. Such changes should usually be detailed in the commit
# that breaks the format and have been previously discussed on the mailing list 
# with general agreement from the core team.
#
OELAYOUT_ABI = "8"

# add poky sanity bbclass
INHERIT += "poky-sanity"

#WARN_QA = "unsafe-references-in-binaries unsafe-references-in-scripts"
WARN_QA = "textrel pkgvarcheck"
ERROR_QA = "dev-so debug-deps dev-deps debug-files arch la2 pkgconfig la perms useless-rpaths rpaths staticdev ldflags"

