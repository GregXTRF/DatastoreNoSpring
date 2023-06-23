import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.datastore.Datastore
import com.google.cloud.datastore.DatastoreOptions
import com.google.cloud.datastore.Entity
import com.google.cloud.datastore.Key
import com.google.cloud.datastore.KeyFactory

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Main {

    private static final String PROJECT_ID = "brep-playground"
    private static final String FILE_PATH = "src/main/resources/brep-playground-9227ad82da9d.json"
    private static final String DATASTORE_KIND = "brep_manual_grakowki"

    static void main(String[] args) {
        DatastoreOptions datastoreOptions = DatastoreOptions.newBuilder()
                .setProjectId(PROJECT_ID)
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(FILE_PATH)))
                .build()

        Datastore datastore = datastoreOptions.service
        KeyFactory keyFactory = datastore.newKeyFactory().setKind(DATASTORE_KIND)
        Key key = keyFactory.newKey(new Random().nextLong())
        Entity entity = Entity.newBuilder(key)
                .set("date_string", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")))
                .build()
        datastore.put(entity)
    }
}
