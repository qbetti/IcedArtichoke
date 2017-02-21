# Iced Artichoke

Projet IntelliJ combinant le PDF Viewer ICEpdf et la libairie de vérification d'intégrité de documents PDF ArtichokeX.
Ne pas oublier de changer le chemin des libraires dans IntelliJ (définis dans File->Project Structure->Modules->Dependencies).

L'exécution de la vérification s'effectue en cliquant sur "Vérifier l'intégrité" dans le menu "Sécurité". Cette action exécute la méthode run() de la classe LifecyleCheck (ca.liflab.check.LifecycleCheck), qui constitue le point d'entrée de toute modification. L'implémentation reste à définir.

ArtichokeX :
https://github.com/liflab/artichoke-x

ICEpdf :
http://www.icesoft.org/java/downloads/icepdf-downloads.jsf
