package za.co.cella.cellaautodebrief;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FireStoreApp {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        InputStream serviceAccount = new FileInputStream("C:\\work-data\\repositories\\firestore_google\\src\\main\\resources\\cellaactus-firebase-adminsdk-91j9k-f4f3fd4e84.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .setDatabaseUrl("https://cellaactus.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);

        Firestore db = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> query = db.collection("routes").get();

        QuerySnapshot querySnapshot = query.get();
        List<DocumentSnapshot> documents = querySnapshot.getDocuments();

        for (DocumentSnapshot document : documents) {
            System.out.println(document.getId());
        }
    }
}
