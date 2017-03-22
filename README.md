# Iced Artichoke

Projet IntelliJ combinant le PDF Viewer ICEpdf, la libairie de vérification d'intégrité de documents PDF ArtichokeX et la livbairie de génération de tests SealTest.
Que ce soit sous IDEA ou Eclipse, importer le projet (et non pas ouvrir) en tant que source.
Ne pas oublier de changer le chemin des libraires dans IntelliJ (définis dans File->Project Structure->Modules->Dependencies) ou Eclipse (clique droit sur projet -> Build Path -> Add external archives...) .

L'exécution de la vérification s'effectue en cliquant sur "Vérifier l'intégrité" dans le menu "Sécurité". Cette action exécute la méthode run() de la classe TriggeredBySecurityButton (ca.liflab.check.TriggeredBySecurityButton), qui constitue le point d'entrée de toute modification. L'implémentation reste à définir.

ArtichokeX :
https://github.com/qbetti/artichoke-x

SealTest :
https://github.com/qbetti/sealtest

ICEpdf :
http://www.icesoft.org/java/downloads/icepdf-downloads.jsf
