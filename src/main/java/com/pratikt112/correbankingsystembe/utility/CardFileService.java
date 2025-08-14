//package com.pratikt112.correbankingsystembe.utility;
//
//import org.springframework.stereotype.Service;
//
//@Service
//public class CardFileService {
//
//    private static final String CARD_DIRECTORY = "config/cards";
//    private final Map<String, Set<String>> cardData = new HashMap<>();
//
//    @PostConstruct
//    public void loadAllCardFiles() throws IOException {
//        Path cardDir = Paths.get(CARD_DIRECTORY);
//
//        if (!Files.exists(cardDir) || !Files.isDirectory(cardDir)) {
//            throw new IllegalStateException("Card file directory not found: " + CARD_DIRECTORY);
//        }
//
//        try (Stream<Path> files = Files.list(cardDir)) {
//            files.filter(path -> path.toString().endsWith(".card"))
//                    .forEach(this::loadCardFile);
//        }
//    }
//
//    private void loadCardFile(Path filePath) {
//        String fileName = filePath.getFileName().toString().replace(".card", "").toUpperCase();
//        try {
//            Set<String> records = Files.lines(filePath)
//                    .map(String::trim)
//                    .filter(line -> !line.isEmpty() && !line.startsWith("#")) // allow comments
//                    .collect(Collectors.toSet());
//            cardData.put(fileName, records);
//        } catch (IOException e) {
//            throw new RuntimeException("Error loading card file: " + filePath, e);
//        }
//    }
//
//    public boolean existsInCard(String cardName, String record) {
//        Set<String> records = cardData.get(cardName.toUpperCase());
//        return records != null && records.contains(record);
//    }
//
//    public Set<String> getCardRecords(String cardName) {
//        return cardData.getOrDefault(cardName.toUpperCase(), Collections.emptySet());
//    }
//}
//
