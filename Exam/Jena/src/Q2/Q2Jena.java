package Q2;

import org.apache.jena.query.*;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

public class Q2Jena {
	public static void main(String[] args) {
		
		 
    	FileManager.get().addLocatorClassLoader(Q2Jena.class.getClassLoader());
		Model model =FileManager.get().loadModel("Q2/Q2Xml.rdf");

		//Les enseignants qui ont le nom de famille ‘Benmohamed’:
		String queryString1 =  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
				"PREFIX EX: <http://www.labged.net#>\n" +
				"SELECT ?prenom ?nom ?titre ?grade ?dateRec ?dateDep\n" +
				"WHERE {\n" +
				"  ?Enseignement EX:prenom ?prenom.\n" +
				"  ?Enseignement EX:nomFamille \"BENMOHAMED\".\n" +
				"  ?Enseignement EX:nomFamille ?nom.\n" +
				"  ?Enseignement EX:titre ?titre.\n" +
				"  ?Enseignement EX:grade ?grade.\n" +
				"  ?Enseignement EX:dateRecrutement ?dateRec.\n" +
				"  OPTIONAL{?Enseignement EX:dateDepart ?dateDep}\n" +
				"}";

		//Les prénoms et date de recrutement des enseignants qui ont le nom de famille ‘Benmohamed’.
		String queryString2 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
				"PREFIX EX: <http://www.labged.net#>\n" +
				"SELECT ?prenom ?dateRec \n" +
				"WHERE {\n" +
				"  ?Enseignement EX:prenom ?prenom.\n" +
				"  ?Enseignement EX:nomFamille \"BENMOHAMED\".\n" +
				"  ?Enseignement EX:dateRecrutement ?dateRec.\n" +
				"}";

		//les prénoms des employés embauchés avant le 2017-03-01.
		String queryString3 ="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
				"PREFIX EX: <http://www.labged.net#>\n" +
				"SELECT ?Prenom  ?DateRecrutement\n" +
				"WHERE {\n" +
				"  ?Enseignement EX:prenom ?Prenom.\n" +
				"  ?Enseignement EX:dateRecrutement ?DateRecrutement.\n" +
				"  FILTER(?DateRecrutement<\"2017-03-01\")\n" +
				"}\n";

		System.out.println("Les enseignants qui ont le nom de famille ‘Benmohamed’:");
		Query query1=QueryFactory.create(queryString1);
		QueryExecution qexec1= QueryExecutionFactory.create(query1, model);
		ResultSet results1 = qexec1.execSelect();
		ResultSetFormatter.out(System.out, results1, query1);

		System.out.println("Les prénoms et date de recrutement des enseignants qui ont le nom de famille ‘Benmohamed’:");
		Query query2=QueryFactory.create(queryString2);
		QueryExecution qexec2= QueryExecutionFactory.create(query2, model);
		ResultSet results2 = qexec2.execSelect();
		ResultSetFormatter.out(System.out, results2, query2);

		System.out.println("les prénoms des employés embauchés avant le 2017-03-01 :");
		Query query=QueryFactory.create(queryString3);
		QueryExecution qexec= QueryExecutionFactory.create(query, model);
		ResultSet results = qexec.execSelect();
		ResultSetFormatter.out(System.out, results, query);

	}
}
