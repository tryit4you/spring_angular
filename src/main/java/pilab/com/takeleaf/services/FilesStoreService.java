package pilab.com.takeleaf.services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStoreService {
    public void init();

  public FileValidationService save(MultipartFile file);

  public Resource load(String filename);

  public void deleteAll();

  public Stream<Path> loadAll();
}
