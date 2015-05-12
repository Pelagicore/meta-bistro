#
# This class is used by the binary recipes created by 
# create_binary_recipe.bbclass. 
#

do_compile() {
}

do_install() {
    # Copy all packages files into ${D}
    cp -r ${WORKDIR}/binary-split/*/* ${D} || true
}

python populate_packages_prepend() {
    import os

    def identify_packages(dir):
        # Identifies which packages are present in dir
        packages = ""
        for d in os.listdir(dir):
            if os.path.isdir(os.path.join(dir, d)):
                packages += " " + d
        print "Packages:", packages
        return packages.lstrip(" ")

    def setup_files(dir):
        # Create a list of the files in a package then can be used as FILES_${PN}
        files_pn = ""
        predir = os.getcwd()
        os.chdir(dir)
        for root, dirs, files in os.walk("."):
            for file in files:
                files_pn += " " + os.path.join(root, file).lstrip(".").replace(" ", "*")
        os.chdir(predir)
        return files_pn

        # Identify the different packages and add those packages to PACKAGES
        # and also populate FILES_* so include those files.

    pkgdir = bb.data.getVar('WORKDIR', d, 1) + "/binary-split"
    packages = identify_packages(pkgdir)
    for p in packages.split(" "):
        files_pn = setup_files(os.path.join(pkgdir, p))
        bb.data.setVar('FILES_' + p, files_pn, d)
    bb.data.setVar('PACKAGES', packages, d)
}
