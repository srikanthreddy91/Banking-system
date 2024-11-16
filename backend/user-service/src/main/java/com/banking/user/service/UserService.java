package com.banking.user.service;

import com.banking.user.dto.UserRequestDTO;
import com.banking.user.dto.UserResponseDTO;
import com.banking.user.exception.UserAlreadyExistsException;
import com.banking.user.exception.UserNotFoundException;
import com.banking.user.model.User;
import com.banking.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Register a new user
     */
    @Transactional
    public UserResponseDTO registerUser(UserRequestDTO userRequest) {
        // Check if email already exists
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new UserAlreadyExistsException(
                    "Email already registered: " + userRequest.getEmail()
            );
        }

        // Create new user
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword()); // Note: We'll add password encoding later
        user.setPhoneNumber(userRequest.getPhoneNumber());

        // Save user
        User savedUser = userRepository.save(user);

        // Return response
        return convertToDTO(savedUser);
    }

    /**
     * Get user by ID
     */
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return convertToDTO(user);
    }

    /**
     * Get user by email
     */
    @Transactional(readOnly = true)
    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return convertToDTO(user);
    }

    /**
     * Convert User entity to UserResponseDTO
     */
    private UserResponseDTO convertToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setStatus(user.getStatus());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }
}


// Add to existing UserService class:

private final PasswordEncoder passwordEncoder;  // Add this field

@Transactional
public UserResponseDTO registerUser(UserRequestDTO userRequest) {
    if (userRepository.existsByEmail(userRequest.getEmail())) {
        throw new UserAlreadyExistsException("Email already registered: " + userRequest.getEmail());
    }

    User user = new User();
    user.setFirstName(userRequest.getFirstName());
    user.setLastName(userRequest.getLastName());
    user.setEmail(userRequest.getEmail());
    // Encode password before saving
    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    user.setPhoneNumber(userRequest.getPhoneNumber());

    User savedUser = userRepository.save(user);
    return convertToDTO(savedUser);
}